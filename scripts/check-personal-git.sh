#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
login=$(git config --local --get personal.githubLogin || true)
email=$(git config --local --get personal.email || true)
remote=$(git config --local --get personal.remote || true)
if [[ -z $login || -z $email || -z $remote ]]; then
  echo 'Personal GitHub identity has not been configured. Commit/push blocked.' >&2
  exit 1
fi
[[ $(git config --local --get user.name) == "$login" ]] || exit 1
[[ $(git config --local --get user.email) == "$email" ]] || exit 1
[[ $(git var GIT_AUTHOR_IDENT) == "$login <$email> "* ]] || exit 1
[[ $(git var GIT_COMMITTER_IDENT) == "$login <$email> "* ]] || exit 1
[[ $remote == "https://github.com/$login/chaoxing-auto-ai.git" ]] || exit 1
[[ $(git remote get-url origin) == "$remote" ]] || exit 1
[[ $(git remote get-url --push origin) == "$remote" ]] || exit 1
[[ $(git remote get-url --push upstream) == 'disabled://upstream-read-only' ]] || exit 1
echo 'Personal repository identity and remotes verified.'
