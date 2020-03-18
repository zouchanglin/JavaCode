package remote

import cm "github.com/easierway/concurrent_map"
import 	"testing"

func TestRemotePackage(t *testing.T)  {
	concurrentMap := cm.CreateConcurrentMap(99)
	concurrentMap.Set(cm.StrKey("key"), 10)
	t.Log(concurrentMap.Get(cm.StrKey("key")))
}