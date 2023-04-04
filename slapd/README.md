# slapd
Slapd is a stand-alone LDAP daemon. It listens for LDAP connections on any number of ports (default 389), responding to the LDAP operations it receives over these connections

# LDIF Configuration
We can define the data for LDAP database in a LDIF file.

Let’s define some sample organization, users and groups in three LDIF files.
slapd/configurations/ou.ldif
slapd/configurations/users.ldif
slapd/configurations/groups.ldif