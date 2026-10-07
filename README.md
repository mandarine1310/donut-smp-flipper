# Donut SMP Flipper

This repository contains a Fabric mod for Minecraft 1.21.1 that adds a lightweight "item flipper" workflow.

Features:
- right-click menu from a custom item
- automatic price comparison
- buy best value / sell best value simulation
- stop-loss management
- price history tracking for the last hours
- simple in-game statistics

## Development

```bash
./gradlew build
```

## How to use

1. Launch Minecraft with Fabric 1.21.1.
2. Open the creative inventory and search for the Flipper Tool.
3. Right-click it to open the menu.
4. Toggle auto-flip, set stop-loss, and inspect price statistics.

## Notes

This is intended as a starter implementation for a real-world item flipper. In a full production mod, the price data would be backed by a real server-side economy system, a database, or live market APIs.
