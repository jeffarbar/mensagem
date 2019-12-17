docker build -t jeffersonfarias/mensagem-backend:latest  -f Dockerfile .
docker login --username=jeffersonfarias
docker push jeffersonfarias/mensagem-backend:latest
