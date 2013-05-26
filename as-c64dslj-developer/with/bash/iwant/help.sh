#!/bin/bash

# $Id: help.sh 443 2013-04-04 11:47:03Z wipu_ $

set -eu

cyg() {
  local IN=$1
  case "$(uname)" in
    CYGWIN*) cygpath --windows -a "$IN" ;;
    *) echo "$IN" ;;
  esac
}

AS_SOMEONE=$(dirname "$0")/../../..
AS_SOMEONE=$(cd "$AS_SOMEONE" && pwd)
CYG_AS_SOMEONE=$(cyg "$AS_SOMEONE")

# TODO how to define this only once:
CACHED=$(cyg "$AS_SOMEONE/.i-cached")

CLASSES=$CACHED/.internal/entry-classes
CYG_CLASSES=$(cyg "$CLASSES")
mkdir -p "$CLASSES"
CYG_SRC=$(cyg "$AS_SOMEONE/with/java/net/sf/iwant/entry/Iwant.java")
javac -d "$CYG_CLASSES" "$CYG_SRC"

java \
  -Xmx1024m \
  -XX:MaxPermSize=256m \
  -cp "$CYG_CLASSES" net.sf.iwant.entry.Iwant "$CYG_AS_SOMEONE" "$@"
