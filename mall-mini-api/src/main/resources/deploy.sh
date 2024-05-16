#!/bin/bash
#
# Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
#

cd /data/mall/mini/ && ./stop.sh && sudo rm mall-mini-api.jar && sudo rz -ybe && ./startup.sh