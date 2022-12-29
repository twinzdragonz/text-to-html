Git Workflow

We will using different branch to do the development based on case . We will have 2 parallel long-running branches :

main - strictly used for release only .

develop - create from master , has all completed stable feat prepared for next release .

feature - create from a new feature from develop branch

release - after feature has merge into develop branch and all of the feature that we require in release has been in the develop branch , we create a release branch and test it before merge it to master and push to production and merge back to develop branch .

hotfix - create from master branch and directly merge to master . Only do when we require quick patch to production issue . Merge back to develop and any feature branch that are active .

bugfix - fix bugs in release

For commit message please use this as guide -

feat: The new feature you're adding to a particular application
fix: A bug fix
style: Feature and updates related to styling
refactor: Refactoring a specific section of the codebase
test: Everything related to testing
docs: Everything related to documentation
chore: Regular code maintenance.[ You can also use emojis to represent commit types]
You will require to use this convention to commit a message .

For branch name we will using git commit hooks to validate branch name .
