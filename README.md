# Web3J Demo application in Android

### Project architecture
- Android Clean Architecture
- Dependency Injection: Koin
- Jetpack Compose
- Kotlin Flow

### How to run the project
1. Download and install the [Web3J CLI](https://github.com/web3j/web3j-cli#installation) (in order to compile Java contract wrappers)
2. Download and install [Solidity compiler](https://docs.soliditylang.org/en/v0.8.6/installing-solidity.html)
3. `cd app/src/main/solidity`
4. Add run permission to bash script `chmod +x compile-wrapper.sh`
5. Run bash script `./compile-wrapper.sh`
6. Rinse and repeat step 3-5 every time you need to update the contract code

#### Bash script explanation
1. Compile Solidity smart contract `solc HelloWorld.sol --bin --abi --optimize -o ./`. This command generates .bin and .abi files for the smart contract, which we'll need in order to compile the Java contract wrapper
2. Compile Java contract wrapper from .bin and .abi files generated by Solidity compiler `web3j generate solidity -a HelloWorld.abi -b HelloWorld.bin -o ../java --package=com.linh.web3jsample.data.contract`
