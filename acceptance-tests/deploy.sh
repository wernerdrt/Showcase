
#!/usr/bin/env bash
set -e

START_TIME=$SECONDS

./upload-serenity-reports.sh

ELAPSED_TIME=$(($SECONDS - $START_TIME))
echo "Upload serenity reports duration: $ELAPSED_TIME seconds"
