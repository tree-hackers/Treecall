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

}