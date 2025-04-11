#!/bin/bash
echo $(ls | tr -d 'aA' | tr 'a-z' 'A-Z')