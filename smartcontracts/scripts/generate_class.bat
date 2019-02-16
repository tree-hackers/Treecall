ren ..\build\compiled\FactoryParts_sol_FactoryParts.abi FactoryParts.abi
ren ..\build\compiled\FactoryParts_sol_FactoryParts.bin FactoryParts.bin
call web3j-3.3.1\bin\web3j solidity generate ../build/compiled/FactoryParts.bin ../build/compiled/FactoryParts.abi -o ../../factory-backend/src/main/java -p com.ethmeff.factorybackend.blockchain.contract
pause