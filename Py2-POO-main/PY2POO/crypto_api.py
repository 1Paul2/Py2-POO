#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys, base64
from Crypto.Cipher import DES3, AES
from Crypto.Hash   import SHA256
from Crypto.Util.Padding import pad, unpad

# ──────────────── validar argumentos ────────────────
if len(sys.argv) != 4:
    print("args: {aes|3des} {encrypt|decrypt} clave", file=sys.stderr)
    sys.exit(1)

alg, mode, key = sys.argv[1:]
alg   = alg.lower()
mode  = mode.lower()

if alg not in ("aes", "3des") or mode not in ("encrypt", "decrypt"):
    print("args: {aes|3des} {encrypt|decrypt} clave", file=sys.stderr)
    sys.exit(1)

# ──────────────── preparar clave ────────────────
def mk_aes_key(k):
    """AES-128: primeros 16 bytes del SHA-256 de la clave textual"""
    return SHA256.new(k.encode()).digest()[:16]

def mk_3des_key(k):
    """3DES requiere 24 bytes (168 bit); rellenamos con hash"""
    raw = SHA256.new(k.encode()).digest()  # 32 bytes
    return raw[:24]

if   alg == "aes":  key_bytes = mk_aes_key(key)
else:               key_bytes = mk_3des_key(key)

# CBC con IV = 0 (¡solo para demo!)
iv = bytes(16) if alg == "aes" else bytes(8)

# ──────────────── leer el texto por stdin ────────────────
data = sys.stdin.read().encode()

# ──────────────── cifrar / descifrar ────────────────
if alg == "aes":
    cipher = AES.new(key_bytes, AES.MODE_CBC, iv)
else:
    cipher = DES3.new(key_bytes, DES3.MODE_CBC, iv)

try:
    if mode == "encrypt":
        ct = cipher.encrypt(pad(data, cipher.block_size))
        sys.stdout.write(base64.b64encode(ct).decode())
    else:
        pt = unpad(cipher.decrypt(base64.b64decode(data)), cipher.block_size)
        sys.stdout.write(pt.decode(errors="replace"))
except Exception as e:
    print("Error en {}: {}".format(alg, e), file=sys.stderr)
    sys.exit(2)
