sed -i '' "s/stagingdemo/$1/g" 'src/test/resources/properties/env.properties'
sed -i '' "s/mind/$2/g" 'src/test/resources/properties/env.properties'
sed -i '' "s/matter/$3/g" 'src/test/resources/properties/env.properties'
sed -i '' "s/TestFeature/$4/g" 'src/test/java/automation/PestRoutes/TestRunner/Feature.java'