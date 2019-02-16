pragma solidity >=0.4.22 <0.6.0;
pragma experimental ABIEncoderV2; // cannot return variable length objects or structs without this, remove when no longer experimental

contract FactoryParts {
    address factoryOwner;

    struct PartElement {
        string name;
        int batch;
        address owner;
        bool recalled;
        address[] subContracts;
        string[] subIds;
    }
    mapping (string => PartElement) private partsList;

    event MarkedAsRecalled(
        string id,
        address owner
    );

    event OwnerChanged(
        string id,
        address owner,
        address newOwner
    );

    constructor() public {
        factoryOwner = msg.sender;
    }

    function storePart(string memory _id, string memory _name, int _batch, address[] memory _subContracts, string[] memory _subIds) 
                            public onlyOwner returns(bool success) {      
        PartElement memory part = partsList[_id];

        part.name = _name;
        part.batch = _batch;
        part.owner = msg.sender;
        part.subContracts = _subContracts;
        part.subIds = _subIds;
        part.recalled = false;

        partsList[_id] = part;
        return true;
    }

    function markAsRecalled(string memory _id) public onlyOwner returns(bool success) {
        PartElement memory part = partsList[_id];
        part.recalled = true;
        emit MarkedAsRecalled(_id, part.owner);
        partsList[_id] = part;

        return true;
    }

    function changeOwner(string memory _id, address _newOwner) public returns(bool success) {
        PartElement memory part = partsList[_id];
        
        require(
            msg.sender == part.owner,
            "Only part owner can call this function."
        );

        address oldOwner = part.owner;
        part.owner = _newOwner;
        partsList[_id] = part;

        emit OwnerChanged(_id, oldOwner, _newOwner);
        
        return true;
    }

    function getPart(string memory _id) public view returns (string memory, int, address, bool, address[] memory, string[] memory) {
        PartElement memory part = partsList[_id];
        return (part.name, part.batch, part.owner, part.recalled, part.subContracts, part.subIds);
    }

    modifier onlyOwner {
        require(
            msg.sender == factoryOwner,
            "Only factory owner can call this function."
        );
        _;
    }
}