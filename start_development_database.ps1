$lines = Get-Content ..\secrets\secrets.env
foreach ($line in $lines) {
    $a,$b = $line.split('=')
    Set-Variable $a $b
}


$ip=docker-machine ip
Write-Host ""
Write-Host ""
Write-Host "Make sure to have the following entry in the C:\Windows\System32\drivers\etc\hosts" -foreground Yellow
Write-Host "$ip   hsm-database" -foreground Yellow
Write-Host ""
Write-Host ""

docker stop hsm-database
docker rm -v hsm-database
docker run --name hsm-database -e POSTGRES_PASSWORD=${postgres_password} -p 5432:5432 -d postgres:10.0