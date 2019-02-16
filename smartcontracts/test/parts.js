const truffleAssert = require('truffle-assertions');
var factoryParts = artifacts.require("../contracts/FactoryParts.sol");

contract('FactoryParts', function(accounts) {
    var id = "63e8a38b-b8a4-4579-8c84-28caedff820d";
    var name = "airbag";
    var batch = "12345";
    var partId = "2c5380ac-06e7-42ff-8131-b0e1a2222eb7";
    var owner = accounts[0];
    var parentId = ["be9a7977-8658-497e-aac0-0d8af4b1f606", "bd6bde1a-4d29-4fb8-9057-e792e74d85ab"]

    it("store and retrieve a part", function() {
        return factoryParts.deployed().then(function(instance) {
            return instance.getPart(id).then(function(retrievedPart){
                assert.equal('', retrievedPart[2]);
            
                return instance.storePart(id, name, batch, partId, owner, parentId).then(function(value){
                    assert(value);
           
                    return instance.getPart(id).then(function(retrievedPartAgain){
                        assert.equal(owner, retrievedPartAgain[3]);
                        assert(!retrievedPartAgain[4]);
                        assert.equal(retrievedPartAgain[5][0], parentId[0])
                        assert.equal(retrievedPartAgain[5][1], parentId[1])
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
                    
                    return instance.markAsRecalled(id).then(function(result) {
                        assert(result);

                        truffleAssert.eventEmitted(result, 'MarkedAsRecalled', (ev) => {
                            return ev[0] === id && ev[1] === accounts[0];
                        });

                        return instance.getPart(id).then(function(retrievedPartAgain){
                            assert(!retrievedPartAgain[4]);
                        });
                    });
                });
            });
        });
    });
});