#!/bin/bash
#
# Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
#

cd /data/mall/admin/ && ./stop.sh && sudo rm mall-admin-api.jar && sudo rz -ybe && ./startup.sh