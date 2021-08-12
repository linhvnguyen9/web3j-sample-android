#!/bin/sh
solc ERC721.sol --bin --abi --optimize -o ./
web3j generate solidity -a ERC721.abi -b ERC721.bin -o ../java --package=com.linh.web3jsample.data.contract