#!/usr/bin/env bash
# Integration of X.org for starting firefox with serenity:
# https://docs.travis-ci.com/user/gui-and-headless-browsers/#Using-xvfb-to-Run-Tests-That-Require-a-GUI
set -e # Exit with nonzero exit code if anything fails

export DISPLAY=:99.0
sh -e /etc/init.d/xvfb start
sleep 3 # give xvfb some time to start
