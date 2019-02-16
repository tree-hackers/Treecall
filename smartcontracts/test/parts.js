const truffleAssert = require('truffle-assertions');
var factoryParts = artifacts.require("../contracts/FactoryParts.sol");

contract('FactoryParts', function(accounts) {
    var id = "63e8a38b-b8a4-4579-8c84-28caedff820d";
    var name = "airbag";
    var batch = "12345";
    var owner = accounts[0];
    var parentId = ["be9a7977-8658-497e-aac0-0d8af4b1f606", "bd6bde1a-4d29-4fb8-9057-e792e74d85ab"]

    it("store and retrieve a part", function() {
        return factoryParts.new().then(function(instance) {
            return instance.getPart(id).then(function(retrievedPart){
                assert.equal('', retrievedPart[0]);
            
                return instance.storePart(id, name, batch, parentId).then(function(value){
                    assert(value);
           
                    return instance.getPart(id).then(function(retrievedPartAgain){
                        assert.equal(owner, retrievedPartAgain[2]);
                        assert(!retrievedPartAgain[3]);
                        assert.equal(retrievedPartAgain[4][0], parentId[0])
                        assert.equal(retrievedPartAgain[4][1], parentId[1])
                    });
                });
            });
        });
    });

    it("store and retrieve a part as non-factory owner", function() {
        return factoryParts.new().then(function(instance) {
            return instance.getPart(id).then(function(retrievedPart){
                assert.equal('', retrievedPart[0]);
            
                return truffleAssert.reverts(instance.storePart(id, name, batch, parentId, { from: accounts[1] }), "Only factory owner can call this function.").then(function(value) { 
                    assert(!value);
           
                    return instance.getPart(id).then(function(retrievedPartAgain){
                        assert(!retrievedPartAgain[3]);
                        assert.equal('', retrievedPart[0]);        
                    });
                });
            });
        });
    });


    it("mark part recalled", function() {
        return factoryParts.new().then(function(instance) {
            return instance.storePart(id, name, batch, parentId).then(function(value) {
                assert(value);

                return instance.getPart(id).then(function(retrievedPart){
                    assert(!retrievedPart[3]);
                    
                    return instance.markAsRecalled(id).then(function(result) {
                        assert(result);

                        truffleAssert.eventEmitted(result, 'MarkedAsRecalled', (ev) => {
                            return ev[0] === id && ev[1] === accounts[0];
                        });

                        return instance.getPart(id).then(function(retrievedPartAgain){
                            assert(retrievedPartAgain[3]);
                        });
                    });
                });
            });
        });
    });


    it("mark part recalled as non-factory owner", function() {
        return factoryParts.new().then(function(instance) {
            return instance.storePart(id, name, batch, parentId).then(function(value) {

                return instance.getPart(id).then(function(retrievedPart){
                    assert(!retrievedPart[3]);

                    return truffleAssert.reverts(instance.markAsRecalled(id, { from: accounts[1] }), "Only factory owner can call this function.").then(function(result) { 
                        assert(!result);

                        return instance.getPart(id).then(function(retrievedPartAgain){
                            assert(!retrievedPartAgain[3]);
                        });
                    });
                });
            });
        });
    });

    it("change owner of a part", function() {
        return factoryParts.new().then(function(instance) {
            return instance.storePart(id, name, batch, parentId).then(function(value) {
                assert(value);

                return instance.getPart(id).then(function(retrievedPart){
                    assert.equal(owner, retrievedPart[2]);
                    
                    const newOwner = accounts[1];
                    return instance.changeOwner(id, newOwner, {from: owner}).then(function(result) {
                        assert(result);

                        return instance.getPart(id).then(function(retrievedPartAgain){
                            assert.equal(newOwner, retrievedPartAgain[2]);
                        });
                    });
                });
            });
        });
    });


    it("change owner of a part as non-owner", function() {
        return factoryParts.new().then(function(instance) {
            return instance.storePart(id, name, batch, parentId).then(function(value) {
                assert(value);

                return instance.getPart(id).then(function(retrievedPart) {
                    assert.equal(owner, retrievedPart[2]);
                    
                    const newOwner = accounts[1];
                    return truffleAssert.reverts(instance.changeOwner(id, newOwner, {from: accounts[3]}), "Only part owner can call this function.").then(function(result) {
                        assert(!result);

                        return instance.getPart(id).then(function(retrievedPartAgain){
                            assert.equal(owner, retrievedPartAgain[2]);
                        });
                    });
                });
            });
        });
    });
});