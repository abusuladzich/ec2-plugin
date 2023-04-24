import com.amazonaws.services.ec2.model.InstanceType
import hudson.model.*
import hudson.plugins.ec2.*
import jenkins.model.Jenkins
import hudson.slaves.Cloud



def call(){
    echo "hello"
    sshPortToConnectWith = '22'
    def slaveTemplateUsEast1Parameters = [
            ami:                           'ami-AAAAAAAA',
            associatePublicIp:             false,
            spotConfig:                    null,
            connectBySSHProcess:           false,
            connectUsingPublicIp:          false,
            customDeviceMapping:           '',
            deleteRootOnTermination:       true,
            description:                   'Jenkins agent EC2 US East 1',
            ebsOptimized:                  true,
            iamInstanceProfile:            '',
            idleTerminationMinutes:        '5',
            initScript:                    '',
            instanceCapStr:                '2',
            javaPath:                      'java',
            jvmopts:                       '',
            labelString:                   'aws.ec2.us.east.jenkins.worker',
            launchTimeoutStr:              '',
            numExecutors:                  '1',
            unixData:                      null,
            remoteFS:                      '',
            remoteAdmin:                   'ec2-user',
            tmpDir:                        '',
            securityGroups:                'sg-11111111',
            stopOnTerminate:               false,
            subnetId:                      'subnet-SSSSSSSS',
            tags:                          null,
            type:                          't2.medium',
            useDedicatedTenancy:           false,
            useEphemeralDevices:           false,
            usePrivateDnsName:             false,
            userData:                      '',
            zone:                          '',
            metadataEndpointEnabled:       true,
            metadataTokensRequired:        true, // `true` enforces IMDSv2 only (over IMDSv1), an important AWS security best practice
            metadataHopsLimit:             1,
            minimumNumberOfInstances:      0,
            minimumNumberOfSpareInstances: 0,
            maxTotalUses:                  -1,
            monitoring:                    false,
            t2Unlimited:                   false,
            connectionStrategy:            ConnectionStrategy.valueOf('PRIVATE_IP'),
            hostKeyVerificationStrategy:   HostKeyVerificationStrategyEnum.valueOf('CHECK_NEW_HARD'),
            tenancy:                       Tenancy.valueOf('Default'),
            ebsEncryptRootVolume:          EbsEncryptRootVolume.valueOf('ENCRYPTED'),
            nodeProperties:                null
    ]
    SlaveTemplate template = new SlaveTemplate(
            slaveTemplateUsEast1Parameters.ami,
            slaveTemplateUsEast1Parameters.zone,
            slaveTemplateUsEast1Parameters.spotConfig,
            slaveTemplateUsEast1Parameters.securityGroups,
            slaveTemplateUsEast1Parameters.remoteFS,
            InstanceType.fromValue(slaveTemplateUsEast1Parameters.type),
            slaveTemplateUsEast1Parameters.ebsOptimized,
            slaveTemplateUsEast1Parameters.labelString,
            Node.Mode.NORMAL,
            slaveTemplateUsEast1Parameters.description,
            slaveTemplateUsEast1Parameters.initScript,
            slaveTemplateUsEast1Parameters.tmpDir,
            slaveTemplateUsEast1Parameters.userData,
            slaveTemplateUsEast1Parameters.numExecutors,
            slaveTemplateUsEast1Parameters.remoteAdmin,
            new UnixData(null, null, null, "22", null),
            slaveTemplateUsEast1Parameters.javaPath,
            slaveTemplateUsEast1Parameters.jvmopts,
            slaveTemplateUsEast1Parameters.stopOnTerminate,
            slaveTemplateUsEast1Parameters.subnetId,
            [new EC2Tag('Name', 'jenkins-worker')],
            slaveTemplateUsEast1Parameters.idleTerminationMinutes,
            slaveTemplateUsEast1Parameters.minimumNumberOfInstances,
            slaveTemplateUsEast1Parameters.minimumNumberOfSpareInstances,
            slaveTemplateUsEast1Parameters.instanceCapStr,
            slaveTemplateUsEast1Parameters.iamInstanceProfile,
            slaveTemplateUsEast1Parameters.deleteRootOnTermination,
            slaveTemplateUsEast1Parameters.useEphemeralDevices,
            slaveTemplateUsEast1Parameters.launchTimeoutStr,
            slaveTemplateUsEast1Parameters.associatePublicIp,
            slaveTemplateUsEast1Parameters.customDeviceMapping,
            slaveTemplateUsEast1Parameters.connectBySSHProcess,
            slaveTemplateUsEast1Parameters.monitoring,
            slaveTemplateUsEast1Parameters.t2Unlimited,
            slaveTemplateUsEast1Parameters.connectionStrategy,
            slaveTemplateUsEast1Parameters.maxTotalUses,
            slaveTemplateUsEast1Parameters.nodeProperties,
            slaveTemplateUsEast1Parameters.hostKeyVerificationStrategy,
            slaveTemplateUsEast1Parameters.tenancy,
            slaveTemplateUsEast1Parameters.ebsEncryptRootVolume,
            slaveTemplateUsEast1Parameters.metadataEndpointEnabled,
            slaveTemplateUsEast1Parameters.metadataTokensRequired,
            slaveTemplateUsEast1Parameters.metadataHopsLimit,
    )

    for (Cloud cloud : Jenkins.instance.clouds) {
        cloud.addTemplate(template)
        for (SlaveTemplate temp :  cloud.getTemplates()){
            println(temp.getAmi())
        }
    }


    Jenkins.instance.save()

}