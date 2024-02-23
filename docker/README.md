## Docker setting

### arm64 architecture nGrinder docker-compose run

```bash
$ sh {PROJECT_HOME}/docker/ngrinder/prepare.sh
$ docker-compose -f {PROJECT_HOME}/docker/ngrinder/docker-compose-ngrinder.yaml up -d
```

After running the above command, you can access the nGrinder web console at `http://localhost:80`.

