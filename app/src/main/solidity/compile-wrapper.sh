#!/bin/sh
solc NonFungibleToken4.sol --bin --abi --optimize -o ./
web3j generate solidity -a NonFungibleToken4.abi -b NonFungibleToken4.bin -o ../java --package=com.linh.web3jsample.data.contract