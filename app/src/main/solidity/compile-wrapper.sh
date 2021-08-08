#!/bin/sh
solc HelloWorld.sol --bin --abi --optimize -o ./
web3j generate solidity -a HelloWorld.abi -b HelloWorld.bin -o ../java --package=com.linh.web3jsample.data.contract