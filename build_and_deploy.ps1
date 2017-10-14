$lines = Get-Content ..\secrets\secrets.env
foreach ($line in $lines) {
    $a,$b = $line.split('=')
    Set-Variable $a $b
}

mvn clean install

plink -l ${ipinterative_user} -pw ${ipinteractive_ssh_passphrase} -i ..\secrets\ipinteractive.ppk ${ipinterative_ip} rm -rf ~${ipinterative_user}/build_temp
plink -l ${ipinterative_user} -pw ${ipinteractive_ssh_passphrase} -i ..\secrets\ipinteractive.ppk ${ipinterative_ip} mkdir ~${ipinterative_user}/build_temp
plink -l ${ipinterative_user} -pw ${ipinteractive_ssh_passphrase} -i ..\secrets\ipinteractive.ppk ${ipinterative_ip} mkdir ~${ipinterative_user}/build_temp/maven

# copy to server
pscp -scp -l ${ipinterative_user} -pw ${ipinteractive_ssh_passphrase} -i ..\secrets\ipinteractive.ppk target\hsm-arduino-backend-webapp.jar ${ipinterative_ip}:~${ipinterative_user}/build_temp/maven/
pscp -scp -l ${ipinterative_user} -pw ${ipinteractive_ssh_passphrase} -i ..\secrets\ipinteractive.ppk Docker/Dockerfile ${ipinterative_ip}:~${ipinterative_user}/build_temp/.
pscp -scp -l ${ipinterative_user} -pw ${ipinteractive_ssh_passphrase} -i ..\secrets\ipinteractive.ppk Docker/build.sh ${ipinterative_ip}:~${ipinterative_user}/build_temp/.

# execute remote commands
plink -l ${ipinterative_user} -pw ${ipinteractive_ssh_passphrase} -i ..\secrets\ipinteractive.ppk ${ipinterative_ip} chmod 700 ~${ipinterative_user}/build_temp/build.sh
plink -l ${ipinterative_user} -pw ${ipinteractive_ssh_passphrase} -i ..\secrets\ipinteractive.ppk ${ipinterative_ip} ~${ipinterative_user}/build_temp/build.sh