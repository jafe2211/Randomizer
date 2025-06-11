# Randomizer  
[![GitHub release](https://img.shields.io/github/release/jafe2211/Randomizer?include_prereleases=&sort=semver)](https://github.com/jafe2211/Randomizer)  
[![License](https://img.shields.io/badge/License-GPLv3-blue)](#license)

A Minecraft plugin for **version 1.21** that randomizes block drops.  
Created by **Jafe2211**, inspired by the original plugin from [motz0815](https://github.com/motz0815/Randomizer).  
This version includes **multiple modes**.

---

## Getting Started

First, choose the mode you want to play. There are currently **three modes** available:

- `player`: Each player gets their own unique drop table.  
- `team`: Each team shares a unique drop table.  
- `single`: Everyone shares the same drop table.

> ⚠️ The more unique drop tables there are, the more resources are required. Consider this if you're running the plugin on a large server.

### Setup Instructions

1. Make sure all players (or teams) that require a unique drop table are online.
2. Set the desired mode using:
   
   `/rm mode <player|team|single>`
3. Shuffle the block drops (required every time you change the mode):

   `/rm shuffle`
4. Start the randomizer:

    `/rm start`
5. To stop the randomizer (without deleting the plugin or resetting the drop table):

    `/rm stop`
