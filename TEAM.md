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
3. Merge `release/{version}` into `main`.
4. Add tag named `{version}` to merge commit.
5. Release GitHub with tag set above and update `What's Changed`(if needed).
6. Release to maven central.

    ```bash
    $ sbt
    > +publishSigned
    > sonatypeBundleRelease
    ```
7. Change `version` in `build.sbt` to `{version + 1}-SNAPSHOT` and update Mima setting.
