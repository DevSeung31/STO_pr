// SPDX-License-Identifier: MIT
pragma solidity ^0.8.28;

import {ERC20} from "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import {Ownable} from "@openzeppelin/contracts/access/Ownable.sol";

contract StoToken is ERC20, Ownable {
    uint8 private immutable _customDecimals;

    event TradeRecorded(
        uint256 indexed tradeId,
        address indexed seller,
        address indexed buyer,
        uint256 quantity,
        uint256 price
    );

    constructor(
        string memory name_,
        string memory symbol_,
        uint256 initialSupply_,
        uint8 decimals_,
        address treasury_
    ) ERC20(name_, symbol_) Ownable(msg.sender) {
        _customDecimals = decimals_;
        _mint(treasury_, initialSupply_);
    }

    function decimals() public view override returns (uint8) {
        return _customDecimals;
    }

    function recordTrade(
        uint256 tradeId,
        address seller,
        address buyer,
        uint256 quantity,
        uint256 price
    ) external onlyOwner {
        emit TradeRecorded(tradeId, seller, buyer, quantity, price);
    }
}