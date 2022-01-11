job('NodeJS Docker example') {
    scm {
        // git('git@github.com/atulbaldaniya/docker-demo-master.git') {  node -> // is hudson.plugins.git.GitSCM
        git('https://github.com/atulbaldaniya/docker-demo-master.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
        }
    triggers {
        scm('* * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in
    // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            // repositoryName('wardviaene/docker-nodejs-demo')
            repositoryName('atulkanhasoft/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
    }
