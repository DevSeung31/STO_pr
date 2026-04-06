const hre = require("hardhat")

async function main() {
    const tokenName = "Sample STO Token";
    const tokenSymbol = "SST";
    const initialSupply = 1000000;
    const decimals = 18;

    const treasuryAddress ="0x8972240bfefc997a4ddfec5d1caf7a7c02ca46e0";
    const StoToken = await hre.ethers.getContractFactory("StoToken");
    const contract = await StoToken.deploy(
        tokenName,
        tokenSymbol,
        initialSupply,
        decimals,
        treasuryAddress
    );

    await contract.waitForDeployment();

    const contractAddress = await contract.getAddress();
    console.log("StoToken deployed to:", contractAddress);
}
main().catch((error) => {
    console.error(error);
    process.exitCode = 1;
});