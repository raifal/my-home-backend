$lines = Get-Content ..\secrets\secrets.env
foreach ($line in $lines) {
    $a,$b = $line.split('=')
    Set-Variable $a $b
}

docker stop hsm-database
docker rm -v hsm-database
docker run --name hsm-database -e POSTGRES_PASSWORD=${postgres_password} -p 5432:5432 -d postgres:10.0