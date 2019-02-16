const solc = require('solc');
const fs = require('fs');
const path = require('path');

const FactoryPartsContractPath = path.resolve(__dirname, '..', 'contracts', 'FactoryParts.sol');
const source = fs.readFileSync(FactoryPartsContractPath, 'UTF-8');

const input =  {
    language: 'Solidity',
    sources: {
      'FactoryParts.sol': {
        content: source
      }
    },
    settings: {
      outputSelection: {
        '*': {
          '*': [ '*' ]
        }
      }
    }
  };

const output = JSON.parse(solc.compile(JSON.stringify(input), path => {
  console.log('path', path);
  return {error: 'helo'}
}))
const compiled = output.contracts['FactoryParts.sol']['FactoryParts'];
fs.writeFileSync(path.resolve(__dirname, '..', 'build', 'compiled', 'FactoryParts.json'), JSON.stringify(compiled, '', '  '), 'UTF-8');
const FactoryPartsJSON = {
  bytecode: '0x' + compiled.evm.bytecode.object,
  deployedBytecode: '0x' + compiled.evm.deployedBytecode.object,
  abi: compiled.abi
};
fs.writeFileSync(path.resolve(__dirname, '..', 'build', 'compiled', 'FactoryParts.json'), JSON.stringify(FactoryPartsJSON, '', '  '), 'UTF-8');
fs.writeFileSync(path.resolve(__dirname, '..', 'build', 'compiled', 'FactoryParts_sol_FactoryParts.bin'), compiled.evm.bytecode.object, 'UTF-8');
fs.writeFileSync(path.resolve(__dirname, '..', 'build', 'compiled', 'FactoryParts_sol_FactoryParts.abi'), JSON.stringify(compiled.abi), 'UTF-8');