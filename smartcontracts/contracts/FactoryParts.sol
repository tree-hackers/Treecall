pragma solidity >=0.4.22 <0.6.0;

contract FactoryParts {
    struct PartElement {
        string name;
        int batch;
        string partId;
        address owner;
        bool recalled;
        string previousIds;
    }
    mapping (string => PartElement) private partsList;

    event MarkedAsRecalled(
        string id,
        address owner
    );

    constructor() public {

    }

    function storePart(string memory _id, string memory _name, int _batch, string memory _partId, address _owner, string memory _previousIds) 
                            public returns(bool success) {      
        PartElement memory part = partsList[_id];

        part.name = _name;
        part.batch = _batch;
        part.partId = _partId;
        part.owner = _owner;
        part.previousIds = _previousIds;
        part.recalled = false;

        partsList[_id] = part;
        return true;
    }

    function markAsRecalled(string memory _id) public returns(bool success) {
        PartElement memory part = partsList[_id];
        part.recalled = true;
        emit MarkedAsRecalled(_id, part.owner);

        return true;
    }

    function getPart(string memory _id) public view returns (string memory, int, string memory, address, bool, string memory) {
        PartElement memory part = partsList[_id];
        return (part.name, part.batch, part.partId, part.owner, part.recalled, part.previousIds);
    }
}