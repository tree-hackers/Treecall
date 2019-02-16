var FactoryParts = artifacts.require("./FactoryParts.sol");

module.exports = function(deployer) {
  deployer.deploy(FactoryParts);
};