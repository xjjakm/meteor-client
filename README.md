
<p align="center">
<img src="https://meteorclient.com/icon.png" alt="meteor-client-logo" width="15%"/>
</p>

<h1 align="center">Meteor</h1>
<p align="center">A Minecraft Fabric Utility Mod for anarchy servers.</p>

<div align="center">
    <a href="https://discord.gg/bBGQZvd"><img src="https://img.shields.io/discord/689197705683140636?logo=discord" alt="Discord"/></a>
    <br>
    <img src="https://img.shields.io/github/last-commit/MeteorDevelopment/meteor-client" alt="GitHub last commit"/>
    <img src="https://img.shields.io/github/commit-activity/w/MeteorDevelopment/meteor-client" alt="GitHub commit activity"/>
    <img src="https://img.shields.io/github/contributors/MeteorDevelopment/meteor-client" alt="GitHub contributors"/>
    <br>
    <img src="https://img.shields.io/github/languages/code-size/MeteorDevelopment/meteor-client" alt="GitHub code size in bytes"/>
    <img src="https://img.shields.io/endpoint?url=https://ghloc.vercel.app/api/MeteorDevelopment/meteor-client/badge?filter=.java$&label=lines%20of%20code&color=blue" alt="GitHub lines of code"/>
</div>

### update-26.3 branch note

This branch greatly references code from https://github.com/robotum1/meteor-client — most of the bulk blaze3d → renderpearl migration was done by @robotum1. Additional work on top of that:

- Cleaned up shader files: removed all `.frag` / `.vert` leftovers, `.fsh` / `.vsh` is now the only convention renderpearl resolves
- Fixed `SodiumDefaultFluidRendererMixin` runtime failures: `@Local` no longer captures the neighbor state (now queried inline) and the 4-param `isFluidSideExposed` got inlined by Sodium 0.9.2, so retargeted to the 5-param public entrypoint
- Fixed `TitleScreenMixin` NPE during `Minecraft.<init>` — `GameRenderer.extract()` runs before `Config` is constructed; added null guards
- Fixed baritone preLaunch crash when `ComeCommandMixin` has `"required": true` on Fabric Loom 26.3 (remapping timing) — changed to `"required": false` + present check in `MixinPlugin`
- Temporarily removed `MeshVertexConsumerMixin` — Sodium 0.9.2 for 26.3 does not yet expose injectable targets after renderpearl adoption
- Updated access widener (`blaze3d.opengl` → `renderpearl.backend.opengl`, removed dead `RenderSetup.outputTarget`, added `LayerRenderState.quads`)

## Usage

### Building
- Clone this repository
- Run `./gradlew build`

### Installation
Follow the [guide](https://meteorclient.com/faq/installation) on the wiki.

## Contributions
We will review and help with all reasonable pull requests as long as the guidelines below are met.

- The license header must be applied to all java source code files.
- IDE or system-related files should be added to the `.gitignore`, never committed in pull requests.
- In general, check existing code to make sure your code matches relatively close to the code already in the project.
- Favour readability over compactness.
- If you need help, check out the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) for a reference.

## Bugs and Suggestions
Bug reports and suggestions should be made in this repo's [issue tracker](https://github.com/MeteorDevelopment/meteor-client/issues) using the templates provided.  
Please provide as much information as you can to best help us understand your issue and give a better chance of it being resolved.

## Donations
All of our work is completely free and non-profit (donations pay only for hosting costs), therefore we are very grateful for all donations made to support us in running our community.  
Donations can be made via our [website](https://meteorclient.com/donate) and the minimum amount to get donor benefits is €5.  
You will be rewarded with a role on our Discord server and a customisable in-game cape.  
⚠️ _Make sure to create a Meteor account and link your Discord and Minecraft accounts to fully experience your rewards._ ⚠️

## Credits
[Cabaletta](https://github.com/cabaletta) and [WagYourTail](https://github.com/wagyourtail) for [Baritone](https://github.com/cabaletta/baritone)  
The [Fabric Team](https://github.com/FabricMC) for [Fabric](https://github.com/FabricMC/fabric-loader) and [Yarn](https://github.com/FabricMC/yarn)

## Licensing
This project is licensed under the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html). 

If you use **ANY** code from the source:
- You must disclose the source code of your modified work and the source code you took from this project. This means you are not allowed to use code from this project (even partially) in a closed-source and/or obfuscated application.
- You must state clearly and obviously to all end users that you are using code from this project.
- Your application must also be licensed under the same license.

*If you have any other questions, check our [FAQ](https://meteorclient.com/faq) or ask in our [Discord](https://meteorclient.com/discord) server.*
