sunjeets-onboarding-root
===================================


Auto-generated by the nf-java-project generator @5.6.5:
https://stash.corp.netflix.com/projects/CPIE/repos/generator-nf-java-project

Owned by: sunjeets@netflix.com

[Your generated service description goes here.]

To manage your metrics dashboards read [DASHBOARDS.md](dashboards/DASHBOARDS.md)

# Publishing Artifacts
Use [tag-to-release](http://go/tag-to-release) to publish your client libraries.

Actions which will publish artifacts:

Push to Master                -> Publish snapshot
Push to other branch          -> Publish snapshot
Push a tag like `v2.0.0-rc.1` -> Publish candidate
Push a tag like `v2.0.0`      -> Publish release

### Project Resources

- Atlas Dashboard: [http://dashboards.prod.netflix.net/show/sunjeetsonboardingroot](http://dashboards.prod.netflix.net/show/sunjeetsonboardingroot)
- Jenkins:
    - Jobs:
        - ![Build Status](https://engedu.builds.test.netflix.net/buildStatus/icon?job=SUN-sunjeets-onboarding-root-build)[Build](https://engedu.builds.test.netflix.net/job/SUN-sunjeets-onboarding-root-build)
        - ![Build Status](https://engedu.builds.test.netflix.net/buildStatus/icon?job=SUN-sunjeets-onboarding-root-build-pull-request)[Build Pull Request](https://engedu.builds.test.netflix.net/job/SUN-sunjeets-onboarding-root-build-pull-request)
        - ![Build Status](https://engedu.builds.test.netflix.net/buildStatus/icon?job=SUN-sunjeets-onboarding-root-update-dependencies-lock)[Update Dependencies](https://engedu.builds.test.netflix.net/job/SUN-sunjeets-onboarding-root-update-dependencies-lock)
    - Server: engedu
- CI Pipeline Flowchart: [http://go/defaultjavacicdpipeline](http://go/defaultjavacicdpipeline)
- Spinnaker:
    - Application: [Spinnaker Application](http://spinnaker.prod.netflix.net/#/applications/sunjeetsonboardingroot)
    - Pipelines:
        - [Deploy To Test Pipeline](http://spinnaker.prod.netflix.net/#/applications/sunjeetsonboardingroot/executions?pipeline=deploy-to-test)
        - [Release Pipeline](http://spinnaker.prod.netflix.net/#/applications/sunjeetsonboardingroot/executions?pipeline=deploy-to-prod)
    - Securitygroups:
        - [Test Load Driver Security Group](http://spinnaker.prod.netflix.net/#/applications/sunjeetsonboardingroot/securityGroups)
        - [Test Security Group](http://spinnaker.prod.netflix.net/#/applications/sunjeetsonboardingroot/securityGroups)
        - [Prod Security Group](http://spinnaker.prod.netflix.net/#/applications/sunjeetsonboardingroot/securityGroups)
