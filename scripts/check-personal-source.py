from pathlib import Path
import re
import tomllib
import xml.etree.ElementTree as ET

root = Path(__file__).resolve().parents[1]
android = '{http://schemas.android.com/apk/res/android}'
manifest = ET.parse(root / 'app/src/main/AndroidManifest.xml')
application = manifest.getroot().find('application')
assert application.get(android + 'allowBackup') == 'false'
assert application.get(android + 'usesCleartextTraffic') == 'false'
for path in (root / 'app/src/main/res/xml').glob('*.xml'):
    ET.parse(path)
tomllib.loads((root / 'gradle/libs.versions.toml').read_text())
build = (root / 'app/build.gradle').read_text()
assert 'applicationId = "io.github.yolo023.chaoxingautoai"' in build
assert 'signingConfigs.chaoxingsignfaker' not in build
for path in (root / 'app/src/main/java').rglob('*.kt'):
    text = path.read_text()
    assert not re.search(r'^import (?:com\.umeng|io\.sentry|org\.aquamarine5\.brainspark\.stackbricks)', text, re.M), path
    assert not re.search(r'https?://[^"\s]*(?:supabase|cdn\.aquamarine5)', text), path
for name in ['app/build.gradle', 'settings.gradle', 'gradle/libs.versions.toml']:
    text = (root / name).read_text()
    assert not re.search(r'com\.umeng|io\.sentry|stackbricks|GHP_TOKEN', text, re.I), name
print('Personal package, XML, dependency and remote-service checks passed')
