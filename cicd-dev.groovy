node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/librepoport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/librepoport.git'), string(name: 'PORT_DESCRIPTION', value: 'A library providing C and Python (libcURL like) API for downloading packages and linux repository metadata in rpm-md format' ), string(name: 'BUILD_LINE', value: 'DEV') ]
  }
}
