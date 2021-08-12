#!/bin/sh
solc ERC20.sol --bin --abi --optimize -o ./
web3j generate solidity -a ERC20.abi -b ERC20.bin -o ../java --package=com.linh.web3jsample.data.contract