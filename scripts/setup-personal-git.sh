#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
if [[ $# -ne 3 ]]; then
  echo 'Usage: setup-personal-git.sh <github-login> <github-noreply-email> <https-fork-url>' >&2
  exit 1
fi
login=$1
email=$2
remote=$3
[[ $login =~ ^[A-Za-z0-9][A-Za-z0-9-]*$ ]] || exit 1
[[ $email == "$login@users.noreply.github.com" || $email =~ ^[0-9]+\+${login}@users\.noreply\.github\.com$ ]] || exit 1
[[ $remote == "https://github.com/$login/chaoxing-auto-ai.git" ]] || exit 1
current=$(git remote get-url origin 2>/dev/null || true)
if [[ -n $current && $current != "$remote" ]]; then
  echo 'Existing origin differs; refusing to replace it.' >&2
  exit 1
fi
git config --local user.name "$login"
git config --local user.email "$email"
git config --local user.useConfigOnly true
git config --local personal.githubLogin "$login"
git config --local personal.email "$email"
git config --local personal.remote "$remote"
git config --local credential.helper ''
git config --local credential.useHttpPath true
git config --local core.hooksPath .githooks
if [[ -z $current ]]; then git remote add origin "$remote"; fi
git remote set-url --push upstream disabled://upstream-read-only
bash scripts/check-personal-git.sh
