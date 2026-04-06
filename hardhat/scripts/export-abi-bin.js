const fs = require("fs");
const path = require("path");

function main() {
    const artifactPath = path.join(
        __dirname,
        "..",
        "artifacts",
        "contracts",
        "StoToken.sol",
        "StoToken.json"
    );

    const outputDir = path.join(__dirname, "..", "web3j");

    if (!fs.existsSync(artifactPath)) {
        throw new Error(`Artifact not found: ${artifactPath}`);
    }

    if (!fs.existsSync(outputDir)) {
        fs.mkdirSync(outputDir, { recursive: true });
    }

    const artifact = JSON.parse(fs.readFileSync(artifactPath, "utf8"));

    const abiPath = path.join(outputDir, "StoToken.abi");
    const binPath = path.join(outputDir, "StoToken.bin");

    fs.writeFileSync(abiPath, JSON.stringify(artifact.abi, null, 2), "utf8");

    // Hardhat artifact의 bytecode는 보통 0x로 시작함
    const bytecode = artifact.bytecode.startsWith("0x")
        ? artifact.bytecode.slice(2)
        : artifact.bytecode;

    fs.writeFileSync(binPath, bytecode, "utf8");

    console.log("ABI exported to:", abiPath);
    console.log("BIN exported to:", binPath);
}

main();