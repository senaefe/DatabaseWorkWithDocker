FROM ubuntu:20.04
LABEL authors="seena"

ENTRYPOINT ["top", "-b"]

RUN apt-get update
RUN apt-get install -y python3

COPY .. /app
WORKDIR /app

CMD ["python3", "app.py"]