pragma solidity ^0.8.2;

import "./contracts/token/ERC721/ERC721.sol";
import "./contracts/token/ERC721/extensions/ERC721Enumerable.sol";
import "./contracts/token/ERC721/extensions/ERC721URIStorage.sol";
import "./contracts/security/Pausable.sol";
import "./contracts/access/Ownable.sol";
import "./contracts/token/ERC721/extensions/ERC721Burnable.sol";
import "./contracts/utils/Counters.sol";

contract NonFungibleToken4 is ERC721, ERC721Enumerable, ERC721URIStorage, Pausable, Ownable, ERC721Burnable {
    using Counters for Counters.Counter;

    Counters.Counter private _tokenIdCounter;

    constructor() ERC721("Non-fungible Token 4", "NFT4") {}

    function safeMint(address to) public onlyOwner {
        _safeMint(to, _tokenIdCounter.current());
        _tokenIdCounter.increment();
    }

    function pause() public onlyOwner {
        _pause();
    }

    function unpause() public onlyOwner {
        _unpause();
    }

    function _baseURI() internal pure override returns (string memory) {
        return "https://my-json-server.typicode.com/linhvnguyen9/nft-server-test/nfts/";
    }

    function _beforeTokenTransfer(address from, address to, uint256 tokenId)
    internal
    whenNotPaused
    override(ERC721, ERC721Enumerable)
    {
        super._beforeTokenTransfer(from, to, tokenId);
    }

    function _burn(uint256 tokenId) internal override(ERC721, ERC721URIStorage) {
        super._burn(tokenId);
    }

    function tokenURI(uint256 tokenId)
    public
    view
    override(ERC721, ERC721URIStorage)
    returns (string memory)
    {
        return super.tokenURI(tokenId);
    }

    function supportsInterface(bytes4 interfaceId)
    public
    view
    override(ERC721, ERC721Enumerable)
    returns (bool)
    {
        return super.supportsInterface(interfaceId);
    }

    function getAllURI() public view returns(string[] memory) {
        uint256 totalSupply = totalSupply();
        string[] memory uris = new string[](totalSupply);
        uint256 counter;
        for (counter = 0; counter < totalSupply; counter++) {
            string memory currentUri = tokenURI(counter);
            uris[counter] = currentUri;
        }
        return uris;
    }
}