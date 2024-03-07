#!/usr/bin/env bash
DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
. "${DIR}/../configuration/const.sh"
DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
cd $DIR

docker build --no-cache -t "${IMAGE_TAG}" .
#docker build --no-cache --progress=plain -t "${IMAGE_TAG}" .&> build.log