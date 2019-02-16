var factoryParts = artifacts.require("../contracts/FactoryParts.sol");

contract('FactoryParts', function(accounts) {
    var id = "63e8a38b-b8a4-4579-8c84-28caedff820d";
    var name = "airbag";
    var batch = "12345";
    var partId = "2c5380ac-06e7-42ff-8131-b0e1a2222eb7";
    var owner = accounts[0];
    var parentId = "be9a7977-8658-497e-aac0-0d8af4b1f606"

    it("store and retrieve a part", function() {
        return factoryParts.deployed().then(function(instance) {
            return instance.getPart(id).then(function(retrievedPart){
                assert('' == retrievedPart[2]);
            
                return instance.storePart(id, name, batch, partId, owner, parentId).then(function(value){
                    assert(value);
           
                    return instance.getPart(id).then(function(retrievedPartAgain){
                        assert(owner == retrievedPartAgain[3]);
                        assert(!retrievedPartAgain[4]);
                    });
                });
            });
        });
    });

    it("mark part recalled", function() {
        return factoryParts.deployed().then(function(instance) {
            return instance.storePart(id, name, batch, partId, owner, parentId).then(function(value) {
                assert(value);

                return instance.getPart(id).then(function(retrievedPart){
                    assert(!retrievedPart[4]);
                    
                    return instance.markAsRecalled(id).then(function(value) {
                        assert(value);

                        return instance.getPart(id).then(function(retrievedPartAgain){
                            assert(!retrievedPartAgain[4]);
                        });
                    });
                });
            });
        });
    });
});