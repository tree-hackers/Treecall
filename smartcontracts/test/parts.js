var parts = artifacts.require("../contracts/Parts.sol");

contract('Parts', function(accounts) {
    it("store and retrieve a part", function() {

        var partId = "2c5380ac-06e7-42ff-8131-b0e1a2222eb7";
        var owner = accounts[0];
        var broken = True;
        var parentId = "be9a7977-8658-497e-aac0-0d8af4b1f606"

        return parts.deployed().then(function(instance) {
            return instance.storePart(partId, owner, broken, parentId).then(function(value){
                assert(value);

                instance.getPart(partId).then(function(foundPart){
                    assert.equal(foundPart[0], owner);
                });
            });
        });
    });
});