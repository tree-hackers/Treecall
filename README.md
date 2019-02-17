# Treecall

Car recall numbers have risen significantly over the last years. The recall process potentially involving several layers of manufacturers is time, personal and therefore money consuming. 
We propose a solution that utilizes the Ethereum blockchain to make the recall process fast, secure and efficient. Throughout the whole production process, we digitally mirror all parts and subparts of a car, creating a dynamic tree structure for smart recalls - Treecall.
With this tree, we can on one hand track down the responsible subparts of malfunctioning parts throughout multiple layers and considering various variables, such as combination of parts. On the other hand, we can traverse up the tree to notify superparts about likely future malfunction after a faulty batch has been identified. 
Furthermore, we are able to use the combined data of all the treelayers for predictive analysis, making the recall process smarter and preventing costly malfunctions before their occurrence (AI not yet implemented).

*Our smart contracts and backend are fully unit tested.*

## Requirements
- Something to compile the contracts, [Truffle](https://github.com/trufflesuite/truffle) preferred (version that comes with [SOLC](https://github.com/ethereum/solc-js) >=0.5.0). 
- Ethereum chain (testnet, [Ganache-cli](https://github.com/trufflesuite/ganache-cli) (bundled with Truffle), [Parity](https://github.com/paritytech/parity-ethereum), [Geth](https://github.com/ethereum/go-ethereum), [Pantheon](https://github.com/PegaSysEng/pantheon)).
- Java 1.8.
- Angular 7.
- Some modern version of NPM.
