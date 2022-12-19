FROM docker.io/library/debian:10-slim

# References
# https://github.com/rackerlabs/dockerstack/blob/master/keystone/openldap/Dockerfile
# https://github.com/acme/docker-openldap/blob/master/memberUid/Dockerfile
# https://github.com/larrycai/docker-openldap/blob/master/Dockerfile

# https://github.com/hadolint/hadolint/wiki/DL4006#correct-code
SHELL ["/bin/bash", "-o", "pipefail", "-c"]

# install slapd in noninteractive mode
RUN apt-get update \
    && echo "slapd slapd/root_password password changeit" | debconf-set-selections \
    && echo "slapd slapd/root_password_again password changeit" | debconf-set-selections \
    && echo "slapd slapd/internal/adminpw password changeit" | debconf-set-selections \
    && echo "slapd slapd/internal/generated_adminpw password changeit" | debconf-set-selections \
    && echo "slapd slapd/password2 password changeit" | debconf-set-selections \
    && echo "slapd slapd/password1 password changeit" | debconf-set-selections \
    && echo "slapd slapd/domain string acme.local" | debconf-set-selections \
    && echo "slapd shared/organization string Acme" | debconf-set-selections \
    && echo "slapd slapd/backend string HDB" | debconf-set-selections \
    && echo "slapd slapd/purge_database boolean true" | debconf-set-selections \
    && echo "slapd slapd/move_old_database boolean true" | debconf-set-selections \
    && echo "slapd slapd/allow_ldap_v2 boolean false" | debconf-set-selections \
    && echo "slapd slapd/no_configuration boolean false" | debconf-set-selections \
    && DEBIAN_FRONTEND=noninteractive apt-get install \
        --assume-yes \
        --no-install-recommends \
        ldap-utils \
        slapd \
    && rm --force --recursive /var/lib/apt/lists/*

# ca-certificates is already the newest version (20190110).
# openssl is already the newest version (1.1.1c-1).

COPY configurations/*.ldif /tmp/

# TODO: Use initialization scripts if available.
# TODO: Deprecate this Dockerfile when initialization scripts are used.
# https://github.com/osixia/docker-openldap/issues/20

# Workaround DL3001 for `service slapd start` Command
# https://github.com/hadolint/hadolint/wiki/DL3001

RUN mkdir -p /var/ldap/acme \
    && chown --recursive openldap /var/ldap \
    && /etc/init.d/slapd start \
    && ldapadd -H ldapi:/// -f /tmp/ou.ldif -x -D "cn=admin,dc=acme,dc=local" -w changeit -v \
    && ldapadd -H ldapi:/// -f /tmp/groups.ldif -x -D "cn=admin,dc=acme,dc=local" -w changeit -v \
    && ldapadd -H ldapi:/// -f /tmp/users.ldif -x -D "cn=admin,dc=acme,dc=local" -w changeit -v \
    && rm --verbose /tmp/*.ldif

EXPOSE 389

CMD ["slapd", "-h", "ldap:///" ,"-g", "openldap", "-u", "openldap", "-d", "256"]