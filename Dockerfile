FROM ubuntu:latest

RUN apt-get update && \
    apt-get install -y software-properties-common && \
    apt-add-repository --yes --update ppa:ansible/ansible && \
    apt-get install -y ansible

WORKDIR /ansible

COPY playbook.yml /ansible/playbook.yml

CMD ["ansible-playbook", "playbook.yml"]

