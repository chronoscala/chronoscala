# chronoscala team guide

## Versioning convention
`"{major}.{minor}.{patch}"`

- major
  - when you make some big changes.
- minor
  - when you make backward incompatible API changes.
- patch
  - when you make backward compatible changes.

## Release guide

1. Create new branch named `release/{version}`.
2. Change `version` in `build.sbt` and update `README.md`(if needed).
3. Merge `release/{version}` into master.
4. Add tag named `{version}` to merge commit.
5. Release to maven central.

    ```bash
    $ sbt
    > set scalaVersion := "2.10.6"
    > publishSigned
    > sonatypeRelease
    > set scalaVersion := "2.11.8"
    > publishSigned
    > sonatypeRelease
    ```
6. Change `version` in `build.sbt` to `{version + 1}-SNAPSHOT`.
