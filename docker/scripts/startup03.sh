#!/bin/bash

#Start redis server on 22122
redis-server --port 22122 &

src/dynomite --conf-file=/dynomite/node03.yml -v5