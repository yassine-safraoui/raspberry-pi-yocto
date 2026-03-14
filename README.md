## Overview

This project is a Yocto-based build setup for `raspberrypi4-64`. It uses a custom layer (`meta-custom`) that configures SSH access, Wi‑Fi, and systemd services so you can log into the board and manage background services easily.

## Build instructions

Run the following commands from the project root:

```bash
source setup-env.sh # Set up the Yocto build environment and exports required environment variables
repo init --standalone-manifest -u "file://$PWD/manifest.xml" # Initializes the `repo` workspace using the local `manifest.xml`
repo sync # Syncs all repositories defined in the manifest so your local sources match the manifest configuration
bitbake core-image-base # Build the core-image-base image for raspberrypi4-64
```

## Wi‑Fi configuration

- **What is provided**: Wi‑Fi support is added via `meta-custom`, where systemd-networkd manages the network interface configuration (e.g: DHCP, routing, DNS...) while `wpa_supplicant` handles Wi‑Fi authentication.
- **Configuration file**: The file `meta-custom/recipes-connectivity/wifi-config/files/wpa_supplicant-wlan0.conf.sample` documents the expected format; copy it to `wpa_supplicant-wlan0.conf` and edit it to set your own SSID and password (and any other `wpa_supplicant` options you need).
- **Credentials**: The actual `wpa_supplicant-wlan0.conf` is a standard `wpa_supplicant` configuration file and will contain your private Wi‑Fi credentials, so keep it out of version control.

## Serial console and boot logs

- **Kernel logs on UART**: Kernel boot messages are enabled on the Raspberry Pi UART, so you can see early boot logs over a serial connection, here is a guide explaining how to set this up: <https://www.jeffgeerling.com/blog/2021/attaching-raspberry-pis-serial-console-uart-debugging>.
- **Login over serial**: `systemd-serialgetty` is enabled on the UART, providing a login shell on the serial console once systemd starts.
- **Why this matters**: This serial access is very useful for debugging when SSH or Wi‑Fi are not working or not yet configured.
