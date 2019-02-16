pragma solidity >=0.4.22 <0.6.0;

contract Parts {
    struct PartElement {
        address owner;
        bool recalled;
        string previousIds;
    }
    mapping (string => PartElement) private partsList;

    event MarkedAsRecalled(
        address owner,
        string id
    );

    constructor() public {

    }

    function storePart(string memory _id, address _owner, string memory _previousIds) 
                            public returns(bool success) {      
        PartElement memory part = partsList[_id];

        part.owner = _owner;
        part.previousIds = _previousIds;
        part.recalled = false;

        partsList[_id] = part;
        return true;
    }

    function isRecalled(string memory _id) public view returns (bool) {
        return partsList[_id].recalled;
    }
}