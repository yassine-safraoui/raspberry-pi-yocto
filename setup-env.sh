#!/usr/bin/env bash

export TEMPLATECONF="$(pwd)/meta-custom/conf/templates/default"
rm -rf build/conf
source poky/oe-init-build-env build