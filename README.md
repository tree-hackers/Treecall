# ETH-MEFF

When a car part is faulty or broken, often the part is not recalled, or the recall takes too long and is ineffective at reaching all effected car owners. We propose a solution, in which we save parts of the car in a tree structure in a blockchain (Ethereum). With this, we can track faulty components across different suppliers. This is important, because an airbag made by Bosch might end up in cars by various manufacturers, and storing the fault information in a decentralized manner will speed up recall times, and with that, repairs. In addition, cars can monitor the chain to see if they have a part included in a recall, or that may be recalled soon (by predictive analysis (not implemented)). 

Our smart contracts and backend are of course fully unit tested.

## Requirements
- Something to compile the contracts, [Truffle](https://github.com/trufflesuite/truffle) preferred (version that comes with [SOLC](https://github.com/ethereum/solc-js) >=0.5.0). 
- Ethereum chain (testnet, [Ganache-cli](https://github.com/trufflesuite/ganache-cli) (bundled with Truffle), [Parity](https://github.com/paritytech/parity-ethereum), [Geth](https://github.com/ethereum/go-ethereum), [Pantheon](https://github.com/PegaSysEng/pantheon)).
- Java 1.8.
- Angular 7.
- Some modern version of NPM.